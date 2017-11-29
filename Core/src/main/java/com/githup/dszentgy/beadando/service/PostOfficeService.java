package com.githup.dszentgy.beadando.service;

import com.githup.dszentgy.beadando.exception.NotFoundNameException;
import com.githup.dszentgy.beadando.model.PostOffice;

import java.util.Collection;

public interface PostOfficeService {
    public void addPostOffice(PostOffice postOffice) throws NotFoundNameException;
    public Collection<PostOffice> getPostOffice();
    public PostOffice getPostOfficeByName(String name) throws NotFoundNameException;
    public void deletePostOffice(PostOffice postOffice) throws NotFoundNameException;
}
