package com.newland.configure.center.springboottestapp.test;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ${林锋鹏}
 * @Title: TestFile
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2021/4/2 14:20
 */
public class TestFile {
    public static void main(String[] args) {
        //    new TestFile().test2();
        test3();

    }
    public void test2(){
        String fir = "D:\\codeWork\\testBill\\hadoop";
        ArrayList<File> list = new ArrayList<File>();
        for (File file : new File(fir).listFiles()) {
            if(file.isFile()){
                list.add(file);
            }
        }
        if (list != null && list.size() > 0) {
            Collections.sort(list, new Comparator<File>() {
                public int compare(File file, File newFile) {
                    if (file.lastModified() < newFile.lastModified()) {
                        return 1;
                    } else if (file.lastModified() == newFile.lastModified()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });
            System.out.println(list.get(list.size() -1).getName());
        }
    }

    public void test1()  throws Exception{
        String path ="D:\\home\\streams\\billApp\\ops-imexport-cdr\\ops-export-cdr";
        String pathA="D:\\home\\streams\\billApp\\ops-imexport-cdr\\ops-export-cdr\\bbf.cfg";
        List<File> files = getFiles(path);
        System.out.println(files.size());
        if(files.size() >= 1){
            if(files.size() >= 5) {
                System.out.println("大于5");
                File fileSort = getFileSort(path);
                System.out.println("删除最旧的文件"+ fileSort.getName());
                fileSort.delete();
                System.out.println("替换文件名");
                Calendar calendar = Calendar.getInstance();
                Date time = calendar.getTime();
                // 2. 格式化系统时间
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                String fileName = format.format(time); //获取系统当前时间并将其转换为string类型,fileName即文件名
                updataFileName(path,fileName);
                System.out.println("生成文件");
                new File(pathA).createNewFile();
            }else {
                System.out.println("替换文件名");
                Calendar calendar = Calendar.getInstance();
                Date time = calendar.getTime();
                // 2. 格式化系统时间
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                String fileName = format.format(time); //获取系统当前时间并将其转换为string类型,fileName即文件名
                updataFileName(path,fileName);
                System.out.println("生成配置文件");
                new File(pathA).createNewFile();
            }
        }else {
            System.out.println("生成配置文件");
            boolean newFile = new File(pathA).createNewFile();
            System.out.println(newFile);
        }
    }

    public static void updataFileName(String paths,String pre) throws IOException {

        File f = new File(paths+"\\bbf.cfg");
        System.out.println(f.getName());
        if (f.exists()) {
                  String old = f.getName();
                  String newName = old+"."+pre;
                    System.out.println(newName);
                    String path = paths + File.separator + newName;
            System.out.println(path);
            File file = new File(path);
            boolean b = f.renameTo(file);
        } else {
            System.out.println("File does not exist!");
        }
    }

    public static File getFileSort(String path) {
        List<File> list = getFiles(path);
        if (list != null && list.size() > 0) {
            Collections.sort(list, new Comparator<File>() {
                public int compare(File file, File newFile) {
                    if (file.lastModified() < newFile.lastModified()) {
                        return 1;
                    } else if (file.lastModified() == newFile.lastModified()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            });
        }
        return list.get(list.size()-1);
    }

    public static List<File> getFiles(String realpath) {
        ArrayList<File> files = new ArrayList<File>();
        File realFile = new File(realpath);
        File[] subfiles = realFile.listFiles();
            for (File file : subfiles) {
                if(file.isFile()){
                    String name = file.getName();
                    if(name.contains(".")){
                        String config = name.substring(0, name.lastIndexOf("."));
                        if (config.equals("bbf.cfg")||config.equals("bbf")) {
                            files.add(file);
                        }
                    }
                }
            }
        return files;
    }

    /**
     * 获取路径下的所有文件/文件夹
     * @param directoryPath 需要遍历的文件夹路径
     * @return
     */
    public static List<String> getAllFile(String directoryPath) {
        List<String> list = new ArrayList<String>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println(file.getAbsolutePath());
                //递归调用
                getAllFile(file.getAbsolutePath());
            } else {
                System.out.println(file.getName());
            }
        }
        return list;
    }
    public static void  test3(){
        getAllFile("D:\\默认桌面\\tools\\工作资料\\项目文档");
    }
}
