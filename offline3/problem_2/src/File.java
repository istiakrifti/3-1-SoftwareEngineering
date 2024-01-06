import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class File implements FileSystemComponent{
    private String name;
    private double size;
    private String type;
    private String directory;
    private int component_count;
    private String creation_time;
    private List<FileSystemComponent> childComponent;
    private FileSystemComponent parent;
    private Date c_time;

    public File(String name,double size) {
        this.name = name;
        this.type = "File";
        this.size = size;
        childComponent = new ArrayList<>();
    }

    @Override
    public void ls(String name) {
        System.out.println("Name: " + this.name);
        System.out.println("Type: " + type);
        System.out.println("Size: " + size);
        System.out.println("Directory: " + directory);
        System.out.println("Component Count: " + component_count);
        System.out.println("Creation time: " + creation_time);
    }

    @Override
    public void list() {}

    @Override
    public FileSystemComponent changeDirectory(String name) {
        System.out.println("Can't change directory to a file");
        return null;
    }

    @Override
    public void Delete(String name) {}

    @Override
    public void deleteRecursively(String name) {}

    @Override
    public void mkdrive(String name,FileSystemComponent parent) {
        System.out.println("Can't create a drive from a file");
    }

    @Override
    public void mkdir(String name,FileSystemComponent parent) {
        System.out.println("Can't create a folder from a file");
    }

    @Override
    public void touch(String name, double size,FileSystemComponent parent) {
        System.out.println("Can't create a file from a file");
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
        if(parent instanceof Drive) this.directory = ((Drive) parent).getDirectory()+directory;
        if(parent instanceof Folder) this.directory = ((Folder) parent).getDirectory()+directory;
    }

    public int getComponent_count() {
        return component_count;
    }

    public void setComponent_count(int component_count) {
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

    public void setC_time(Date c_time) {
        this.c_time = c_time;
    }
    public String calculateTime() {
        Date date = c_time;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedTime = dateFormat.format(date);
        return  formattedTime;
    }
}
