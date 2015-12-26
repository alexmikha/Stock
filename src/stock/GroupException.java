package stock;

@SuppressWarnings("serial")
public class GroupException extends Exception {

    @Override
    public String getMessage() {
        return "Dublicate group";
    }


}
