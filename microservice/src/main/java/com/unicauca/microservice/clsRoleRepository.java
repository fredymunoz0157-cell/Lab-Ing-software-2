package com.unicauca.microservice;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_role")
public class clsRoleRepository {

    @Id
    @Column(name = "id_rol")
    private String attOUID;

    @Column(name = "rol_nombre")
    private String attName;

    @Column(name = "rol_descripcion")
    private String attDescription;

    public clsRoleRepository() {
    }

    public clsRoleRepository(String prmOUID, String prmName, String prmDescription) {
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

    public Boolean opModify(String prmName, String prmDescription) {
        attName = prmName;
        attDescription = prmDescription;
        return true;
    }

}
