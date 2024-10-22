package dataprovider;

import core.utils.DataUtils;
import model.*;

public class DataProvider {

    private static final String ACCOUNTS_PATH = "test-data/accounts.json";
    private static final String CREDENTIALS_PATH = "test-data/credentials.json";


    /**
     * DataProvider for AccountDto (login tests)
     */
    @org.testng.annotations.DataProvider(name = "accountDataProvider")
    public Object[][] accountDataProvider() throws Exception {
        return DataUtils.parseJsonData(ACCOUNTS_PATH, AccountDto.class);
    }

    /**
     * DataProvider for CredentialsDto (signup tests)
     */
    @org.testng.annotations.DataProvider(name = "credentialsDataProvider")
    public Object[][] credentialsDataProvider() throws Exception {
        return DataUtils.parseJsonData(CREDENTIALS_PATH, CredentialsDto.class);
    }
}
