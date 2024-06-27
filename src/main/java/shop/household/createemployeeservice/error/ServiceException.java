package shop.household.createemployeeservice.error;

public class ServiceException extends Exception{
    private ExceptionCode code;

    public ServiceException(String message, ExceptionCode code) {
        super(message);
        this.code = code;
    }
}
