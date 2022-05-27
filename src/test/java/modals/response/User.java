package modals.response;

public class User {
    private String contactId;
    private String firstName;
    private String lastName;
    private String role;
    private String isAdmin;
    private String email;
    private String federatedStatus;

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFederatedStatus() {
        return federatedStatus;
    }

    public void setFederatedStatus(String federatedStatus) {
        this.federatedStatus = federatedStatus;
    }
}
