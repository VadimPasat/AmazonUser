package constant;

public enum Messages {
    SUCCESSFUL_LOGIN("Products"),
    HOME_PAGE("New customer");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
