module model {
    requires java.xml.bind;

    opens com.sda.model to java.xml.bind;

    exports com.sda.model;
    exports com.sda.controller;
    exports com.sda.search;
    exports com.sda.sort;
    exports com.sda.exceptions;
}