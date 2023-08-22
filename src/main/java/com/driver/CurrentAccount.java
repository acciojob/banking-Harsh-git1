package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);

        if(balance < 5000) throw new Exception("Insufficient Balance");
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        char[] licenseChars = tradeLicenseId.toCharArray();
        boolean isValid = true;

        for (int i = 1; i < licenseChars.length; i++) {
            if (licenseChars[i] == licenseChars[i - 1]) {
                isValid = false;
                break;
            }
        }

        if (isValid) {
            return; // License ID is already valid
        }

        // Rearrange characters to create a valid license ID
        for (int i = 1; i < licenseChars.length; i += 2) {
            if (licenseChars[i] == licenseChars[i - 1]) {
                int j = i + 1;
                while (j < licenseChars.length && licenseChars[j] == licenseChars[i - 1]) {
                    j++;
                }
                if (j == licenseChars.length) {
                    throw new Exception("Valid License can not be generated");
                }
                char temp = licenseChars[j];
                licenseChars[j] = licenseChars[i];
                licenseChars[i] = temp;
            }
        }

        tradeLicenseId =  new String(licenseChars);
    }

}
