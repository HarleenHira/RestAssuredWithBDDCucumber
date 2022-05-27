package modals.response;

import java.util.List;

public class GetUsersResponse {
    private String id;
    private String environmentName;
    private List<User> organizationUser;

    public List<User> getOrganizationUser() {
        return organizationUser;
    }

    public void setOrganizationUser(List<User> organizationUser) {
        this.organizationUser = organizationUser;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }


}
