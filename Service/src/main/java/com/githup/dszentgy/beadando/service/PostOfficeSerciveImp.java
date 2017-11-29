package com.githup.dszentgy.beadando.service;

import com.githup.dszentgy.beadando.dao.PostOfficeDAO;
import com.githup.dszentgy.beadando.exception.NotFoundNameException;
import com.githup.dszentgy.beadando.model.PostOffice;

import java.util.Collection;

public class PostOfficeSerciveImp implements PostOfficeService {
    public PostOfficeDAO postOfficeDAO;

    public PostOfficeSerciveImp(PostOfficeDAO postOfficeDAO) {
        this.postOfficeDAO = postOfficeDAO;
    }

    public void addPostOffice(PostOffice postOffice) throws NotFoundNameException{
        PostOffice old = postOfficeDAO.readPostOfficeByName(postOffice.getName());
        if (old == null) {
            postOfficeDAO.createPostOffice(postOffice);
        } else {

            PostOffice updated = new PostOffice(
                    postOffice.getType(),
                    postOffice.getName(),
                    postOffice.getPrice() + old.getPrice(),
                    postOffice.getQuantity() + old.getQuantity(),
                    postOffice.getPostOfficeCategory());

            postOfficeDAO.updatePostOffice(updated);
        }
    }

    public Collection<PostOffice> getPostOffice() {
        return postOfficeDAO.readPostOffice();
    }
    public PostOffice getPostOfficeByName(String name) throws NotFoundNameException{
        PostOffice old = postOfficeDAO.readPostOfficeByName(name);
        if (old == null) {
            throw new NotFoundNameException("Does not exist it's a PostOffice name");
        } else {
            return postOfficeDAO.readPostOfficeByName(name);
        }
    }

    public void deletePostOffice(PostOffice postOffice) throws NotFoundNameException{
        PostOffice old = postOfficeDAO.readPostOfficeByName(postOffice.getName());
        if (old == null) {
            throw new NotFoundNameException("Does not exist it's a PostOffice name");
        } else {
            postOfficeDAO.deletePostOffice(postOffice);
        }
    }
}
