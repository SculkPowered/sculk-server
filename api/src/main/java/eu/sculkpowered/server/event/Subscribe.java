package eu.sculkpowered.server.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation that is used to indicate that a method is an event listener.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscribe {

  /**
   * The order of the listener.
   *
   * @return the order.
   */
  short order() default EventOrder.NORMAL;

  /**
   * Whether the listener should be called async.
   *
   * @return whether the listener should be async.
   */
  boolean async() default false;

}
