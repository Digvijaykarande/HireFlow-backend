package HireFlow.hireFlowProject.common.exception;

public class CompanyAlreadyExistsException
        extends RuntimeException {

    public CompanyAlreadyExistsException(
            String message) {

        super(message);
    }
}