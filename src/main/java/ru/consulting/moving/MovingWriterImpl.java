package ru.consulting.moving;

import java.io.*;

public class MovingWriterImpl implements MovingWriter {

    @Override
    public void writeMoving(String filename, String moving) {

        try (BufferedWriter buffer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"))) {

            buffer.write(moving);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedEncodingException encodingException) {
            System.out.println(encodingException.getMessage());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            ;
        }
    }
}
