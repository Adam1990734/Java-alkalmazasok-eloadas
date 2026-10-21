package com.oanda.account;

import com.oanda.Request;
import com.oanda.transaction.TransactionID;

/**
 * AccountChangesRequest
 */
public class AccountChangesRequest extends Request {

    /**
     * AccountChangesRequest Constructor
     * <p>
     * Construct a new AccountChangesRequest
     * <p>
     * @param accountID Account Identifier
     */
    public AccountChangesRequest(AccountID accountID) {
        this.setPathParam("accountID", accountID);

    }

    /**
     * Set the sinceTransactionID
     * <p>
     * ID of the Transaction to get Account changes since.
     * <p>
     * @param sinceTransactionID the sinceTransactionID as a TransactionID
     * @return {@link AccountChangesRequest AccountChangesRequest}
     * @see TransactionID
     */
    public AccountChangesRequest setSinceTransactionID(TransactionID sinceTransactionID)
    {
        this.queryParams.put("sinceTransactionID", sinceTransactionID);
        return this;
    }

    /**
     * Set the sinceTransactionID
     * <p>
     * ID of the Transaction to get Account changes since.
     * <p>
     * @param sinceTransactionID the sinceTransactionID as a String
     * @return {@link AccountChangesRequest AccountChangesRequest}
     * @see TransactionID
     */
    public AccountChangesRequest setSinceTransactionID(String sinceTransactionID)
    {
        this.queryParams.put("sinceTransactionID", new TransactionID(sinceTransactionID));
        return this;
    }
}
