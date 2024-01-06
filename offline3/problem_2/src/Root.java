import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Root implements FileSystemComponent{
    private String name;
    private List<FileSystemComponent> childComponent;
    private FileSystemComponent parent;
    private String directory;
    private static Root root;

    private Root(String name) {
        this.name = name;
        childComponent = new ArrayList<>();
        directory = "";
    }

    public static Root getRoot() {
        if(root==null) root = new Root("root");
        return root;
    }

    @Override
    public void ls(String name) {
        for(int i=0;i< childComponent.size();i++) {
            Drive drive = (Drive)childComponent.get(i);
            if(drive.getName().equals(name)) {
                System.out.println("Name: " + drive.getName());
                System.out.println("Type: " + drive.getType());
                drive.calculateSize();
                System.out.println("Size: " + drive.getSize());
                System.out.println("Directory: " + drive.getDirectory());
                System.out.println("Component Count: " + drive.getComponent_count());
                System.out.println("Creation time: " + drive.getCreation_time());
                return;
            }
        }

        System.out.println(name+" doesn't exist in the current directory");
    }

    @Override
    public void list() {
        for(int i=0;i< childComponent.size();i++) {
            Drive drive = (Drive)childComponent.get(i);
            drive.calculateSize();
            System.out.println(drive.getName() + "\t" + drive.getSize() +" kB"+"\t" + drive.calculateTime());
        }
        if(childComponent.size()==0) System.out.println(name +" is empty currently");
    }

    @Override
    public FileSystemComponent changeDirectory(String name) {
        for(int i=0;i< childComponent.size();i++) {
            if(((Drive)childComponent.get(i)).getDirectory().equals(name)) {
                return childComponent.get(i);
            }
        }
        System.out.println(name+" doesn't exist in the current directory");
        return null;
    }

    @Override
    public void Delete(String name) {
        boolean flag = false;
        for(int i=0;i< childComponent.size();i++) {
            if(((Drive)childComponent.get(i)).getName().equals(name)) {
                if(((Drive) childComponent.get(i)).getComponent_count()==0) {
                    childComponent.remove(i);
                }
                else System.out.println("Can't delete "+ name);
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
            if (((Drive)component).getName().equals(name))
            {
                flag=true;
                ((Drive)component).recursiveDeleteHelper();
                iterator.remove();
                break;
            }
        }
        if(!flag) System.out.println(name+" doesn't exist in the current directory");
    }

    @Override
    public void mkdrive(String name,FileSystemComponent parent) {
        Drive drive = new Drive(name);
        childComponent.add(drive);
        drive.setParent(parent);
        drive.setCreation_time();
        drive.setDirectory(name);
    }

    @Override
    public void mkdir(String name,FileSystemComponent parent) {
        System.out.println("Can't create a folder from root");
    }

    @Override
    public void touch(String name, double size,FileSystemComponent parent) {
        System.out.println("Can't create a file from root");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
}
