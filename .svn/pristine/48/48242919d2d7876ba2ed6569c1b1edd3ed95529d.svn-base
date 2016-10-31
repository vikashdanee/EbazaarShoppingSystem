package business.customersubsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Logger;

import middleware.DbConfigProperties;
import middleware.dataaccess.DataAccessSubsystemFacade;
import middleware.exceptions.DatabaseException;
import middleware.externalinterfaces.DataAccessSubsystem;
import middleware.externalinterfaces.DbClass;
import middleware.externalinterfaces.DbConfigKey;


class DbClassCustomerProfile implements DbClass {
	enum Type {READ, READPAYMENT};
	@SuppressWarnings("unused")
	private static final Logger LOG = 
		Logger.getLogger(DbClassCustomerProfile.class.getPackage().getName());
	private DataAccessSubsystem dataAccessSS = 
    	new DataAccessSubsystemFacade();
    
    private Type queryType;
    
    private String readQuery = "SELECT custid,fname,lname FROM Customer WHERE custid = ?";
    private String readDefaultPaymentQuery = "SELECT * FROM Customer WHERE custid = ?" ;
	
    private Object[] readParams, paymentParams;
    private int[] readTypes, paymentTypes;
    
    /** Used for reading in values from the database */
    private CustomerProfileImpl customerProfile;
    private CreditCardImpl cardInfo;
      
    public CustomerProfileImpl readCustomerProfile(Integer custId) throws DatabaseException {
        queryType = Type.READ;
        readParams = new Object[]{custId};
        readTypes = new int[]{Types.INTEGER};
        dataAccessSS.atomicRead(this); 
        return customerProfile;
    }
    
    public CreditCardImpl readPaymentInfo(Integer custId) throws DatabaseException {
        queryType = Type.READPAYMENT;
        paymentParams = new Object[]{custId};
        paymentTypes = new int[]{Types.INTEGER};
        dataAccessSS.atomicRead(this); 
        return cardInfo;
    }
 
    public void populateEntity(ResultSet resultSet) throws DatabaseException {
        try {   
            //we take the first returned row
        	if(queryType == Type.READ){
	            if(resultSet.next()){
	                customerProfile 
	                  = new CustomerProfileImpl(resultSet.getInt("custid"),
	                							resultSet.getString("fname"),
	                                            resultSet.getString("lname"));
	            }
            }
        	else{
	            if(resultSet.next()){
	                cardInfo 
	                  = new CreditCardImpl(resultSet.getString("nameoncard"),
	                							resultSet.getString("expdate"),
	                                            resultSet.getString("cardnum"),
	                                            resultSet.getString("cardtype"));
	            }
            }
        }
        catch(SQLException e){
            throw new DatabaseException(e);
        }
    }

    public String getDbUrl() {
    	DbConfigProperties props = new DbConfigProperties();	
    	return props.getProperty(DbConfigKey.ACCOUNT_DB_URL.getVal());
    }

    @Override
    public String getQuery() {
    	switch(queryType) {
	    	case READ :
	    		return readQuery;
	    	case READPAYMENT :
	    		return readDefaultPaymentQuery;
	    	default :
	    		return null;
    	}
    }

	@Override
	public Object[] getQueryParams() {
		switch(queryType) {
	    	case READ :
	    		return readParams;
	    	case READPAYMENT :
	    		return paymentParams;
	    	default :
	    		return null;
		}
	}
	@Override
	public int[] getParamTypes() {
		switch(queryType) {
	    	case READ :
	    		return readTypes;
	    	case READPAYMENT :
	    		return paymentTypes;
	    	default :
	    		return null;
		}
	}
}
