package de.bauhd.terminal;

import java.io.Serializable;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.jline.reader.LineReader;

@Plugin(name = "TerminalAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE)
public final class TerminalAppender extends AbstractAppender {

  private static LineReader lineReader;

  public TerminalAppender(final String name, final Filter filter,
      final Layout<? extends Serializable> layout,
      final boolean ignoreExceptions, final Property[] properties) {
    super(name, filter, layout, ignoreExceptions, properties);
  }

  public static void setLineReader(final LineReader lineReader) {
    TerminalAppender.lineReader = lineReader;
  }

  @Override
  public void append(LogEvent event) {
    lineReader.printAbove(this.getLayout().toSerializable(event).toString());
  }

  @PluginBuilderFactory
  public static <A extends Builder<A>> A newBuilder() {
    return new Builder<A>().asBuilder();
  }

  public static final class Builder<A extends AbstractAppender.Builder<A>> extends
      AbstractAppender.Builder<A>
      implements org.apache.logging.log4j.core.util.Builder<TerminalAppender> {

    @Override
    public TerminalAppender build() {
      return new TerminalAppender(this.getName(),
          this.getFilter(),
          this.getLayout(),
          this.isIgnoreExceptions(),
          this.getPropertyArray()
      );
    }
  }

}
