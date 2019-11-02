package freeredis.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class HostAndPort implements Serializable {
    private final StringProperty hostName;
    private final StringProperty host;
    private final StringProperty port;
    private final StringProperty userName;
    private final StringProperty password;

    public HostAndPort() {
        this(null,null,null);
    }

    public HostAndPort(String hostName, String host, String port) {
        this.hostName = new SimpleStringProperty(hostName);
        this.host = new SimpleStringProperty(host);
        this.port = new SimpleStringProperty(port);
        this.userName = new SimpleStringProperty("");
        this.password = new SimpleStringProperty("");
    }

    public String getHostName() {
        return hostName.get();
    }

    public StringProperty hostNameProperty() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName.set(hostName);
    }

    public String getHost() {
        return host.get();
    }

    public StringProperty hostProperty() {
        return host;
    }

    public void setHost(String host) {
        this.host.set(host);
    }

    public String getPort() {
        return port.get();
    }

    public StringProperty portProperty() {
        return port;
    }

    public void setPort(String port) {
        this.port.set(port);
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}
