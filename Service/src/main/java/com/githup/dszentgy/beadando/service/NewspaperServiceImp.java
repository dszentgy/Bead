package com.githup.dszentgy.beadando.service;

import com.githup.dszentgy.beadando.dao.NewspaperDAO;
import com.githup.dszentgy.beadando.exception.NotFoundNameException;
import com.githup.dszentgy.beadando.model.Newspaper;

import java.util.Collection;

public class NewspaperServiceImp implements NewspapersService {
    public NewspaperDAO newspaperDAO;

    public NewspaperServiceImp(NewspaperDAO newspaperDAO) {
        this.newspaperDAO = newspaperDAO;
    }

    public void addNewspaper(Newspaper newspaper) throws NotFoundNameException{
        Newspaper oldNewspaper = newspaperDAO.readNewspaperByName(newspaper.getName());
        if (oldNewspaper == null) {
            newspaperDAO.createNewspaper(newspaper);
        } else {

            Newspaper updated = new Newspaper(newspaper.getType(),
                    newspaper.getName(),
                    newspaper.getPrice() + oldNewspaper.getPrice(),
                    newspaper.getQuantity() + oldNewspaper.getQuantity(),
                    newspaper.getNewspaperCategory());

            newspaperDAO.updateNewspaper(updated);
        }
    }

    public Collection<Newspaper> getNewspaper() {
        return newspaperDAO.readNewspaper();
    }

    public Newspaper getNewspaperByName(String name) throws NotFoundNameException{
        Newspaper old = newspaperDAO.readNewspaperByName(name);
        if (old == null) {
            throw new NotFoundNameException("Does not exist it's a Newspaper name");
        } else {
            return newspaperDAO.readNewspaperByName(name);
        }
    }


    public void deleteNewspaper(Newspaper newspaper) throws NotFoundNameException{
       Newspaper old = newspaperDAO.readNewspaperByName(newspaper.getName());
       if (old == null){
           throw new NotFoundNameException("Does not exist it's a Newspaper name");
       }else {
           newspaperDAO.deleteNewspaper(newspaper);
       }

    }
}
