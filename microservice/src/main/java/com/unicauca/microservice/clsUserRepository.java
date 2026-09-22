package com.unicauca.microservice;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_user")
public class clsUserRepository {

    @Id
    @Column (name = "id_usuario")
    private String attOUID;

    @Column (name = "usuario_nombre")
    private String attName;

    @Column (name = "usuario_descripcion")
    private String attDescription;

    @Column (name = "usuario_nickname")
    private String attNickName;

    @Column (name = "usuario_password")
    private String attPassword;

    @Column (name = "usuario_estado")
    private Boolean attAsset;

    @Column (name = "id_rol")
    private String attRole;

    public clsUserRepository() {
    }

    public clsUserRepository(String prmOUID, String prmName, String prmDescription, String prmNickName, String prmPassword, Boolean prmAsset, String prmRole) {
        attOUID = prmOUID;
        attName = prmName;
        attDescription = prmDescription;
        attNickName = prmNickName;
        attPassword = prmPassword;
        attAsset = prmAsset;
        attRole = prmRole;
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

    public String opGetNickName() {
        return attNickName;
    }

    public String opGetPassword() {
        return attPassword;
    }

    public Boolean opGetAsset() {
        return attAsset;
    }

    public String opGetRole() {
        return attRole;
    }

    public void opModify(String prmName, String prmDescription, String prmNickName, String prmPassword, Boolean prmAsset, String prmRole) {
        attName = prmName;
        attDescription = prmDescription;
        attNickName = prmNickName;
        attPassword = prmPassword;
        attAsset = prmAsset;
        attRole = prmRole;
    }
    
}
