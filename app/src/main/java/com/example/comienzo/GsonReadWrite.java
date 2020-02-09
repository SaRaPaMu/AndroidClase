package com.example.comienzo;

import android.content.Context;
import android.content.res.Resources;

import com.example.comienzo.model.Episodio;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class GsonReadWrite {
    Context ctx;
    Resources res;

    public GsonReadWrite(Context ctx) {
        this.ctx = ctx;
        res = ctx.getResources();
    }

    public List<Episodio> read(){


        Gson gson = new Gson();
        File file = new File("datos.dat");

        byte[] alldata = null;

        try (InputStream input = new FileInputStream(file)) {

            ByteArrayOutputStream arr = new ByteArrayOutputStream();

            int leidos;

            byte[] data = new byte[1024];

            while ((leidos = input.read(data)) != -1) {
                arr.write(data, 0, leidos);
            }
            alldata = arr.toByteArray();

        } catch (IOException ex) {

        }

        List<Episodio> episodios = gson.fromJson(alldata.toString(),new TypeToken<List<Episodio>>() {}.getType());

        return episodios;
    }


    public void write(List<Episodio> episodios){
        Gson gson = new Gson();

        String string = gson.toJson(episodios);


        FileOutputStream outputStream;
        File file = new File("datos.dat");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (DataOutputStream write = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {

                write.write(string.getBytes());


        } catch (IOException ex) {
        }

    }


}
