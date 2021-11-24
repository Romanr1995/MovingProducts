package ru.consulting;

import java.io.*;

public class MovingWriterImpl implements MovingWriter {

    @Override
    public void writeMoving(String filename, String moving) {

        try (BufferedWriter buffer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"))) {

            buffer.write(moving);
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (UnsupportedEncodingException e) {
            e.getMessage();
        } catch (IOException exception) {
            exception.getMessage();
        }
    }
}
