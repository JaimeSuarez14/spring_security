package practica.spring_security.dto.user;

import practica.spring_security.enums.AssignableRole;

public class UpdateRoleDTO {
  private AssignableRole role;

  public AssignableRole getRole() {
    return role;
  }

  public void setRole(AssignableRole role) {
    this.role = role;
  }
  
}
