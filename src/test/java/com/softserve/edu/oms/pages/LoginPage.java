package com.softserve.edu.oms.pages;

import com.softserve.edu.atqc.controls.Button;
import com.softserve.edu.atqc.controls.CheckBox;
import com.softserve.edu.atqc.controls.IButton;
import com.softserve.edu.atqc.controls.ICheckBox;
import com.softserve.edu.atqc.controls.ITextField;
import com.softserve.edu.atqc.controls.TextField;

public class LoginPage {
    private class LoginPageUIMap {
        public final ITextField loginName;
        public final ITextField password;
        public final ICheckBox rememberMe;
        public final IButton submit;
        public final IButton reset;

        public LoginPageUIMap() {
            this.loginName = TextField.getByName("j_username");
            this.password = TextField.getByName("j_password");
            this.rememberMe = CheckBox
                    .getByName("_spring_security_remember_me");
            this.submit = Button.getByName("submit");
            this.reset = Button.getByName("reset");
        }
    }

    // Elements
    private LoginPageUIMap controls;

    public LoginPage() {
        controls = new LoginPageUIMap();
    }

    // getters
    
    public ITextField getLoginName() {
        return controls.loginName;
    }
    
    public ITextField getPassword() {
        return controls.password;
    }
    
    public ICheckBox getRememberMe() {
        return controls.rememberMe;
    }
    
    public IButton getSubmit() {
        return controls.submit;
    }
    
    public IButton getReset() {
        return controls.reset;
    }
    
    // setters

    public void setLoginName(String loginName) {
        controls.loginName.sendKeys(loginName);
    }

    public void setPassword(String password) {
        controls.password.sendKeys(password);
    }

    public void rememberMeClick() {
        controls.rememberMe.click();
    }

    public void submitClick() {
        controls.submit.click();
    }

    public void resetClick() {
        controls.reset.click();
    }

}
