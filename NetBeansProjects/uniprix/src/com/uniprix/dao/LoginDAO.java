package com.uniprix.dao;

import java.util.List;

import com.uniprix.model.Login;

public interface LoginDAO {

    public List<Login> getAll();

    public Login getById(long idlogin);

    public void saveLogin(Login login);

    public void delete(long idlogin);

    public void update(Login login);

    public int countAll();
    //public boolean 						verifyLogin(String logintxt, String password);
}
