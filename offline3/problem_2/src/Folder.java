import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Folder implements FileSystemComponent{
    private String name;
    private double size;
    private String type;
    private String directory;
    private int component_count;
    private String creation_time;
    private List<FileSystemComponent> childComponent;
    private FileSystemComponent parent;
    private Date c_time;

    public Folder(String name) {
        this.name = name;
        this.type = "Folder";
        size = 0.0;
        component_count = 0;
        childComponent = new ArrayList<>();
    }

    @Override
    public void ls(String name) {
        for(int i=0;i< childComponent.size();i++) {
            if(childComponent.get(i) instanceof  Folder && ((Folder)childComponent.get(i)).getName().equals(name)) {
                Folder folder = (Folder)childComponent.get(i);
                System.out.println("Name: " + folder.name);
                System.out.println("Type: " + folder.type);
                folder.calculateSize();
                System.out.println("Size: " + folder.size);
                System.out.println("Directory: " + folder.directory.substring(0,folder.directory.length()-1));
                System.out.println("Component Count: " + folder.component_count);
                System.out.println("Creation time: " + folder.creation_time);
                return;
            }
            else if(childComponent.get(i) instanceof  File && ((File)childComponent.get(i)).getName().equals(name)) {
                File file = (File)childComponent.get(i);
                file.ls(name);
                return;
            }
        }
        System.out.println(name+" doesn't exist in the current directory");
    }

    @Override
    public void list() {

        for(int i=0;i< childComponent.size();i++) {
            if(childComponent.get(i) instanceof Folder) {
                Folder folder = (Folder)childComponent.get(i);
                folder.calculateSize();
                System.out.println(folder.name + "\t" + folder.size +" kB"+"\t" + folder.calculateTime());
            }
            else if(childComponent.get(i) instanceof File) {
                File file = (File)childComponent.get(i);
                System.out.println(file.getName() + "\t" + file.getSize() +" kB"+"\t" + file.calculateTime());
            }
        }
        if(childComponent.size()==0) System.out.println(name +" is empty currently");
    }

    @Override
    public FileSystemComponent changeDirectory(String name) {
        for(int i=0;i< childComponent.size();i++) {
            if(childComponent.get(i) instanceof  Folder && ((Folder)childComponent.get(i)).getName().equals(name)) {
                return childComponent.get(i);
            }
            else if(childComponent.get(i) instanceof  File && ((File)childComponent.get(i)).getName().equals(name)) {
                System.out.println("Can't change directory to a file");
                return null;
            }
        }
        System.out.println(name+" doesn't exist in the current directory");
        return null;
    }

    @Override
    public void Delete(String name) {
        boolean flag = false;
        for(int i=0;i< childComponent.size();i++) {
            if(childComponent.get(i) instanceof  Folder && ((Folder)childComponent.get(i)).getName().equals(name)) {
                if(((Folder) childComponent.get(i)).getComponent_count()==0) {
                    childComponent.remove(i);
                }
                else System.out.println("Can't delete "+ name);
                flag=true;
            }
            else if(childComponent.get(i) instanceof  File && ((File)childComponent.get(i)).getName().equals(name)) {
                childComponent.remove(i);
                flag=true;
            }
        }
        if(!flag) System.out.println(name+" doesn't exist in the current directory");
    }

    @Override
    public void deleteRecursively(String name) {
        boolean flag = false;
        Iterator<FileSystemComponent> iterator = childComponent.iterator();
        while (iterator.hasNext())
        {
            FileSystemComponent component = iterator.next();
            if (component instanceof  Folder && ((Folder)component).getName().equals(name))
            {
                flag=true;
                ((Folder)component).recursiveDeleteHelper();
                iterator.remove();
                break;
            }
            else if (component instanceof  File && ((File)component).getName().equals(name))
            {
                flag=true;
                System.out.println("Warning: You are deleting a file recursively");
                iterator.remove();
                break;
            }
        }
        if(!flag) System.out.println(name+" doesn't exist in the current directory");
    }

    public void recursiveDeleteHelper() {
        Iterator<FileSystemComponent> iterator = childComponent.iterator();
        while (iterator.hasNext())
        {
            FileSystemComponent component = iterator.next();
            if(component instanceof Folder)
            {
                ((Folder)component).recursiveDeleteHelper();
            }
            else if(component instanceof File)
            {
                System.out.println("Warning: You are deleting a file recursively");
                iterator.remove();
            }
        }
    }
    @Override
    public void mkdrive(String name,FileSystemComponent parent) {
        System.out.println("Can't create a drive from a folder");
    }

    @Override
    public void mkdir(String name,FileSystemComponent parent) {
        Folder folder = new Folder(name);
        childComponent.add(folder);
        folder.setParent(parent);
        folder.setCreation_time();
        folder.setDirectory(name);
        setComponent_count();
    }

    @Override
    public void touch(String name, double size,FileSystemComponent parent) {
        File file = new File(name,size);
        childComponent.add(file);
        file.setParent(parent);
        file.setCreation_time();
        file.setDirectory(name);
        setComponent_count();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        if(parent instanceof Drive) this.directory = ((Drive)parent).getDirectory()+directory+"\\";
        if(parent instanceof Folder) this.directory = ((Folder)parent).directory+directory+"\\";
    }

    public int getComponent_count() {
        return component_count;
    }

    public void setComponent_count() {
        component_count = childComponent.size();
        this.component_count = component_count;
    }

    public String getCreation_time() {
        return creation_time;
    }

    public void setCreation_time() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM,yyyy hh:mm a");
        c_time = now;
        String formattedTime = dateFormat.format(now);
        this.creation_time = formattedTime;
    }

    public List<FileSystemComponent> getChildComponent() {
        return childComponent;
    }

    public void setChildComponent(List<FileSystemComponent> childComponent) {
        this.childComponent = childComponent;
    }

    public FileSystemComponent getParent() {
        return parent;
    }

    public void setParent(FileSystemComponent parent) {
        this.parent = parent;
    }

    public Date getC_time() {
        return c_time;
    }

    public void setC_time() {
        this.c_time = c_time;
    }

    public String calculateTime() {
        Date date = c_time;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedTime = dateFormat.format(date);
        return  formattedTime;
    }

    public double calculateSize() {
        double sum = 0.0;
        for(int i=0;i< childComponent.size();i++) {
            if(childComponent.get(i) instanceof  Folder) {
                sum += ((Folder)childComponent.get(i)).calculateSize();
            }
            else if(childComponent.get(i) instanceof  File) {
                sum += ((File)childComponent.get(i)).getSize();
            }
        }
        size = sum;
        return size;
    }
}
