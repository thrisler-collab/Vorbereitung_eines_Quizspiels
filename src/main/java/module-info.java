module com.example.netopil {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.netopil to javafx.fxml;
    exports com.example.netopil;
}