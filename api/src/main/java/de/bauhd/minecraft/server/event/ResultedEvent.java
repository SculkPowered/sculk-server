package de.bauhd.minecraft.server.event;

import org.jetbrains.annotations.NotNull;

public abstract class ResultedEvent<T extends ResultedEvent.Result> {

    protected T result;

    public void setResult(@NotNull T result) {
        this.result = result;
    }

    public T getResult() {
        return this.result;
    }

    public static abstract class Result {

        private final boolean allowed;

        public Result(boolean allowed) {
            this.allowed = allowed;
        }

        public boolean isAllowed() {
            return this.allowed;
        }

        public boolean isDenied() {
            return !this.allowed;
        }
    }

    public static final class GenericResult extends Result {

        private static final GenericResult ALLOWED = new GenericResult(true);
        private static final GenericResult DENIED = new GenericResult(false);

        public static GenericResult allowed() {
            return ALLOWED;
        }

        public static GenericResult denied() {
            return DENIED;
        }

        private GenericResult(boolean allowed) {
            super(allowed);
        }
    }
}
