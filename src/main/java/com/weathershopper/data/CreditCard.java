package com.weathershopper.data;

import com.jdiai.tools.DataClass;

public class CreditCard extends DataClass {
    public String email = "test@test.com";
    public String cardNum;
    public String expData;
    public String  cvc;
    public String  zipCode;

    public CreditCard(String ccType) {
        switch (ccType) {
            case "visa":
                cardNum = "4111111111111111";
                expData = "0124";
                cvc = "123";
                zipCode = "12345";
                break;
        }
    }
}
