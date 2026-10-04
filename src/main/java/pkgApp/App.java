package pkgApp;

import pkgPresentation.uiLogin;

import pkgController.clsController;

public class App {
    public static void main(String[] args) throws Exception {
        clsController.opGetInstance().opReady();
        uiLogin varObj = new uiLogin();
        varObj.setVisible(true);
    }
}
