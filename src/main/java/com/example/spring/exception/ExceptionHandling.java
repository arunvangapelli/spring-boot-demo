//package com.example.spring.exception;
//
//import javax.persistence.PersistenceException;
//import javax.servlet.http.HttpServletRequest;
//import java.sql.SQLException;
//
//import com.bbtransact.tss.api.cards.exception.domain.CardNotFoundException;
//import com.bbtransact.tss.api.cards.exception.domain.OperationNotAllowedException;
//import com.bbtransact.tss.api.commons.bean.OperationsErrorBean;
//import com.bbtransact.tss.api.commons.error.ErrorHelper;
//import com.bbtransact.tss.api.commons.exception.ExceptionHandlingBase;
//import com.bbtransact.tss.api.commons.exception.domain.AccountHolderAssociationNotActiveException;
//import com.bbtransact.tss.api.commons.exception.domain.AssociationNotFoundException;
//import com.bbtransact.tss.api.commons.exception.domain.CardHolderNotFoundException;
//import com.bbtransact.tss.api.commons.exception.domain.InternalServiceUserAssociationFailureException;
//import com.bbtransact.tss.api.commons.exception.domain.InvalidAcceptLanguageException;
//import com.bbtransact.tss.api.commons.exception.domain.InvalidDataFormatException;
//import com.bbtransact.tss.api.commons.exception.domain.QueryParamsNotAllowedException;
//import com.bbtransact.tss.api.commons.exception.domain.ResourceNotFoundException;
//import com.bbtransact.tss.api.commons.exception.domain.UnsupportedMediaTypeException;
//import com.bbtransact.tss.api.commons.exception.domain.UserAssociationNotActiveException;
//import com.bbtransact.tss.api.commons.http.HttpConstants;
//import com.bbtransact.tss.api.commons.localization.MessageHandler;
//import org.hibernate.exception.GenericJDBCException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.ConversionNotSupportedException;
//import org.springframework.beans.TypeMismatchException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.sleuth.Tracer;
//import org.springframework.dao.InvalidDataAccessResourceUsageException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.InvalidMediaTypeException;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.http.converter.HttpMessageNotWritableException;
//import org.springframework.transaction.CannotCreateTransactionException;
//import org.springframework.validation.BindException;
//import org.springframework.web.HttpMediaTypeNotAcceptableException;
//import org.springframework.web.HttpMediaTypeNotSupportedException;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingPathVariableException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.ServletRequestBindingException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.multipart.support.MissingServletRequestPartException;
//import org.springframework.web.servlet.NoHandlerFoundException;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//@EnableWebMvc
//@ControllerAdvice
//public class ExceptionHandling extends ExceptionHandlingBase {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandling.class);
//    private static final String CRITICAL_ERROR = "criticalError";
//    private Tracer tracer;
//
//    @Autowired
//    public ExceptionHandling(MessageHandler messageHandler, ErrorHelper errorHelper, Tracer tracer) {
//        this.messageHandler = messageHandler;
//        this.errorHelper = errorHelper;
//        this.tracer = tracer;
//    }
//    
//    @Override
//    public Logger getLogger() {
//        return LOGGER;
//    }
//
//    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//    @ExceptionHandler(value = { UnsupportedMediaTypeException.class,
//            InvalidMediaTypeException.class,
//            HttpMediaTypeNotSupportedException.class })
//    public void handleUnsupportedMediaTypeException(Exception ex) {
//        /*
//         * This method doesn't return any response body, exception or error can be identified by HTTP status code
//         * returned.
//         */
//        getLogger().error("Unsupported or invalid media type exception occurred ", ex);
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = { MissingServletRequestParameterException.class,
//            ServletRequestBindingException.class,
//            TypeMismatchException.class, MissingServletRequestPartException.class,
//            BindException.class })
//    public void handleBadRequestException(Exception ex) {
//        /*
//         * This method doesn't return any response body, exception or error can be identified by HTTP status code
//         * returned.
//         */
//        getLogger().error("Request parameter binding exception occurred ", ex);
//    }
//
//    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
//    public void handleMethodNotAllowedException(HttpRequestMethodNotSupportedException ex) {
//        /*
//         * This method doesn't return any response body, exception or error can be identified by HTTP status code
//         * returned.
//         */
//        getLogger().error("Unsupported request method exception occurred ", ex);
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(value = { MissingPathVariableException.class,
//            ConversionNotSupportedException.class,
//            HttpMessageNotWritableException.class })
//    public void handleInternalServerErrorException(Exception ex) {
//        /*
//         * This method doesn't return any response body, exception or error can be identified by HTTP status code
//         * returned.
//         */
//        getLogger().error("Other exception occurred ", ex);
//    }
//
//    /**
//     * Handles an exception that is thrown when requested url mapping is not found.
//     * 
//     * @param ex Exception
//     */
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(value = { NoHandlerFoundException.class })
//    public void handleNotFoundException(Exception ex) {
//        /*
//         * This method doesn't return any response body, exception or error can be identified by HTTP status code
//         * returned.
//         */
//        getLogger().error("Handler exception occurred ", ex);
//    }
//
//    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
//    @ExceptionHandler(value = HttpMediaTypeNotAcceptableException.class)
//    public void handleMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex) {
//        /*
//         * This method doesn't return any response body, exception or error can be identified by HTTP status code
//         * returned.
//         */
//        getLogger().error("Media type not acceptable exception occurred ", ex);
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public OperationsErrorBean handleGlobalException(final HttpServletRequest request,
//                final Exception ex) {
//        
//        /*
//         * For unhandled exceptions, log message contains the String criticalError so that notifications/alerts can be
//         * sent by parsing the String criticalError. Example log message would look like the below message.
//         * LogMessage=Unhandled exception occurred, is criticalError and exception class java.lang.NullPointerException
//         */
//        getLogger().error("Unhandled exception occurred, is {} and exception {} ", CRITICAL_ERROR, ex.getClass(), ex);
//        return errorHelper.errorResponse("1000", tracer.getCurrentSpan().traceIdString(),
//                messageHandler.localizeErrorMessage("error.1000"), EMPTY_FIELD_INFO,
//                request.getAttribute(HttpConstants.START_TIME));
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(value = { CannotCreateTransactionException.class, SQLException.class,
//            PersistenceException.class,
//            GenericJDBCException.class, InvalidDataAccessResourceUsageException.class })
//    @ResponseBody
//    public OperationsErrorBean handleSQLException(final HttpServletRequest request,
//                final Exception ex) {
//        
//        /*
//         * For database exceptions, log message contains the String criticalError so that notifications/alerts can be
//         * sent by parsing the String criticalError. Example log message would look like the below message.
//         * LogMessage=Database exception occurred, is criticalError and exception class java.sql.SQLException
//         */
//        getLogger().error("Database exception occurred, is {} and exception {} ", CRITICAL_ERROR, ex.getClass(), ex);
//        return errorHelper.errorResponse("1001", tracer.getCurrentSpan().traceIdString(),
//                messageHandler.localizeErrorMessage("error.1001"), EMPTY_FIELD_INFO,
//                request.getAttribute(HttpConstants.START_TIME));
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(value = InvalidDataFormatException.class)
//    @ResponseBody
//    public OperationsErrorBean handleInvalidDataFormatException(final HttpServletRequest request,
//                final InvalidDataFormatException ex) {
//        getLogger().error("Invalid data format exception occurred ", ex);
//        return errorHelper.errorResponse("1002", tracer.getCurrentSpan().traceIdString(),
//                messageHandler.localizeErrorMessage("error.1002"), EMPTY_FIELD_INFO,
//                request.getAttribute(HttpConstants.START_TIME));
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(value = { CardHolderNotFoundException.class, CardNotFoundException.class })
//    @ResponseBody
//    public OperationsErrorBean handleResourceNotFoundException(final HttpServletRequest request,
//                final ResourceNotFoundException ex) {
//        getLogger().error("Resource not found exception occurred ", ex);
//        return errorHelper.errorResponse(ex.getErrorCode(), tracer.getCurrentSpan().traceIdString(),
//                messageHandler.localizeErrorMessage("error." + ex.getErrorCode()), ex.getResourceName(),
//                request.getAttribute(HttpConstants.START_TIME));
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = InvalidAcceptLanguageException.class)
//    @ResponseBody
//    public OperationsErrorBean handleInvalidAcceptLanguageException(
//                final HttpServletRequest request,
//                final InvalidAcceptLanguageException ex) {
//        /*
//         * errorMessage for this method is hard-coded since this is thrown in RequestInterceptor and the
//         * messages.properties files will not be loaded to provide a meaningful response back.
//         */
//        getLogger().error("Invalid Accept-Language exception occurred ", ex);
//        return errorHelper.errorResponse("1004", tracer.getCurrentSpan().traceIdString(),
//                "Request header Accept-Language is not in the expected format.", EMPTY_FIELD_INFO,
//                request.getAttribute(HttpConstants.START_TIME));
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(value = InternalServiceUserAssociationFailureException.class)
//    @ResponseBody
//    public OperationsErrorBean handleInternalServiceUserAssociationFailureException(
//                final HttpServletRequest request,
//                final InternalServiceUserAssociationFailureException ex) {
//        
//        /*
//         * For internal service exceptions, log message contains the String criticalError so that notifications/alerts
//         * can be sent by parsing the String criticalError. Example log message would look like the below message.
//         * LogMessage=Internal service user association failure exception occurred, is criticalError and exception class
//         * com.bbtransact.tss.api.commons.exception.domain.InternalServiceUserAssociationFailureException
//         */
//        getLogger().error("Internal service user association failure exception occurred, is {} and exception {} ",
//                CRITICAL_ERROR, ex.getClass(), ex);
//        return errorHelper.errorResponse("1010", tracer.getCurrentSpan().traceIdString(),
//                messageHandler.localizeErrorMessage("error.1010"), EMPTY_FIELD_INFO,
//                request.getAttribute(HttpConstants.START_TIME));
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = QueryParamsNotAllowedException.class)
//    @ResponseBody
//    public OperationsErrorBean handleQueryParamsNotAllowedException(
//                final HttpServletRequest request,
//                final QueryParamsNotAllowedException ex) {
//        getLogger().error("Query params not allowed exception occurred ", ex);
//        return errorHelper.errorResponse("1011", tracer.getCurrentSpan().traceIdString(),
//                messageHandler.localizeErrorMessage("error.1011"), EMPTY_FIELD_INFO,
//                request.getAttribute(HttpConstants.START_TIME));
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(value = AssociationNotFoundException.class)
//    @ResponseBody
//    public OperationsErrorBean handleAssociationNotFoundException(final HttpServletRequest request,
//                final AssociationNotFoundException ex) {
//        getLogger().error("Association not found exception occurred ", ex);
//        return errorHelper.errorResponse("1014", tracer.getCurrentSpan().traceIdString(),
//                messageHandler.localizeErrorMessage("error.1014"), EMPTY_FIELD_INFO,
//                request.getAttribute(HttpConstants.START_TIME));
//    }
//
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ExceptionHandler(value = UserAssociationNotActiveException.class)
//    @ResponseBody
//    public OperationsErrorBean handleUserAssociationNotActiveException(
//                final HttpServletRequest request,
//                final UserAssociationNotActiveException ex) {
//        getLogger().error("User association not active exception occurred ", ex);
//        return errorHelper.errorResponse("1015", tracer.getCurrentSpan().traceIdString(),
//                messageHandler.localizeErrorMessage("error.1015"), EMPTY_FIELD_INFO,
//                request.getAttribute(HttpConstants.START_TIME));
//    }
//
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ExceptionHandler(value = AccountHolderAssociationNotActiveException.class)
//    @ResponseBody
//    public OperationsErrorBean handleAccountHolderAssociationNotActiveException(
//                final HttpServletRequest request,
//                final AccountHolderAssociationNotActiveException ex) {
//        getLogger().error("Accountholder association not active exception occurred ", ex);
//        return errorHelper.errorResponse("1017", tracer.getCurrentSpan().traceIdString(),
//                messageHandler.localizeErrorMessage("error.1017"), EMPTY_FIELD_INFO,
//                request.getAttribute(HttpConstants.START_TIME));
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(value = HttpMessageNotReadableException.class)
//    @ResponseBody
//    public OperationsErrorBean handleDataTypeMismatchException(final HttpServletRequest request,
//                final HttpMessageNotReadableException ex) {
//        getLogger().error("Message not readable exception occurred ", ex);
//        return errorHelper.errorResponse("1018", tracer.getCurrentSpan().traceIdString(),
//                messageHandler.localizeErrorMessage("error.1018"), EMPTY_FIELD_INFO,
//                request.getAttribute(HttpConstants.START_TIME));
//    }
//    
//    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
//    @ExceptionHandler(value = OperationNotAllowedException.class)
//    @ResponseBody
//    public OperationsErrorBean handleOperationNotAllowedException(final HttpServletRequest request,
//                final OperationNotAllowedException ex) {
//        getLogger().error("Operation not allowed exception occured ", ex);
//        return errorHelper.errorResponse("4012", tracer.getCurrentSpan().traceIdString(),
//                messageHandler.localizeErrorMessage("error.4012"), ex.getFieldName(),
//                request.getAttribute(HttpConstants.START_TIME));
//    }
//
//    /**
//     * sends the Error Response for the Body Validation validated using @Valid annotation
//     */
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    public OperationsErrorBean handleMethodArgumentNotValidException(final HttpServletRequest request,
//                                                                     final MethodArgumentNotValidException ex) {
//        return super.handleMethodArgumentNotValidException(request, tracer.getCurrentSpan().traceIdString(), ex);
//    }
//}
