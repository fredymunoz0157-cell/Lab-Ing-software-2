package com.unicauca.microservicio_crud;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_roles")

public class clsRoleSpringBoot {

    @Id

    private String attOUID;
    private String attName;
    private String attDescription;

    public clsRoleSpringBoot() {}
    
    public clsRoleSpringBoot(String prmOUID, String prmName, String prmDescription) {
        this.attOUID = prmOUID;
        this.attName = prmName;
        this.attDescription = prmDescription;
    }

    public String opGetOUID() {
        return attOUID;
    }

    public String opGetName() {
        return attName;
    }

    public String opGetDescription() {
        return attDescription;
    }

    public void opSetOUID(String prmOUID) {
        this.attOUID = prmOUID;
    }

    public void opSetName(String prmName) {
        this.attName = prmName;
    }

    public void opSetDescription(String prmDescription) {
        this.attDescription = prmDescription;
    }
}