package sio.veliko.Models;

public class User {

    private int idUser;
    private String nomUser;
    private String prenomUser;
    private String emailUser;
    private String isBlocked;
    private int nbResa;


    public User(int idUser, String nomUser, String prenomUser, String emailUser, String isBlocked) {
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.emailUser = emailUser;
        this.isBlocked = isBlocked;
    }
    public User(String nomUser , String prenomUser , int nbResa )
    {
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.nbResa = nbResa;

    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNomUser() {
        return nomUser;
    }


    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getIsBlocked() {
        return isBlocked;
    }

    public void  setIsBlocked(String isBlocked) {
        this.isBlocked = isBlocked;
    }

    public int getNbResa() {
        return nbResa;
    }

    public void setNbResa(int nbResa) {
        this.nbResa = nbResa;
    }
}


