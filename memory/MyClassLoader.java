package com.project.jvm.memory;

import java.io.*;

public class MyClassLoader extends ClassLoader{

    private String classLoaderName;
    private String path;
    private String fileExtension = ".class";

    public MyClassLoader(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }
    public MyClassLoader(ClassLoader parent, String classLoaderName) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }
    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("findClass invoked:"+name);
        System.out.println("classLoader name:"+classLoaderName);
        byte[] data = new byte[0];
        try {
            data = this.loadClassData(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.defineClass(name, data, 0, data.length);
    }

    public byte[] loadClassData(String name) throws IOException {
        InputStream in = null;
        ByteArrayOutputStream out = null;
        String path= name + File.separatorChar +
                name.replace('.',File.separatorChar)+".class";
        System.out.println(path);
        try {
            in = new FileInputStream(path);
            out = new ByteArrayOutputStream();
            byte[] buffer=new byte[2048];
            int len = 0;
            while((len=in.read(buffer))!=-1){
                out.write(buffer,0,len);
            }
            return out.toByteArray();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            in.close();
            out.close();
        }
        return null;
    }
}
