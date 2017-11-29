package com.githup.dszentgy.beadando.service;

import com.githup.dszentgy.beadando.exception.NotFoundNameException;
import com.githup.dszentgy.beadando.model.Newspaper;

import java.util.Collection;

public interface NewspapersService{
   public void addNewspaper(Newspaper newspaper) throws NotFoundNameException;
   public Collection<Newspaper> getNewspaper();
   public Newspaper getNewspaperByName(String name) throws NotFoundNameException;
   public void deleteNewspaper(Newspaper newspaper) throws NotFoundNameException;
}
