package com.githup.dszentgy.beadando.dao;

import com.githup.dszentgy.beadando.exception.NotFoundNameException;
import com.githup.dszentgy.beadando.model.Newspaper;

import java.util.Collection;

public interface NewspaperDAO {
    public Collection<Newspaper> readNewspaper();
    public Newspaper readNewspaperByName(String name);
    public void createNewspaper(Newspaper newspaper);
    public void updateNewspaper(Newspaper newspaper) throws NotFoundNameException;
    public void deleteNewspaper(Newspaper newspaper) throws  NotFoundNameException;
}
