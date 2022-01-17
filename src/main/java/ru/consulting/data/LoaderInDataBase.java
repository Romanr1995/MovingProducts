package ru.consulting.data;

import java.io.IOException;

public interface LoaderInDataBase {

    public void loadProductsInDataBase(String fileName) throws IOException, ClassNotFoundException;
}
