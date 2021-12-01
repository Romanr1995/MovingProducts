package ru.consulting.moving;

import ru.consulting.model.Moving;

import java.util.List;

public interface MovingWriter {

     void writeMoving(String filename, List<List<Moving>> movings);
}
