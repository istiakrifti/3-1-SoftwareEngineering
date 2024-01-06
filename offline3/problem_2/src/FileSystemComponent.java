public interface FileSystemComponent {
    void ls(String name);
    void list();
    FileSystemComponent changeDirectory(String name);
    void Delete(String name);
    void deleteRecursively(String name);
    void mkdrive(String name,FileSystemComponent parent);
    void mkdir(String name,FileSystemComponent parent);
    void touch(String name,double size,FileSystemComponent parent);
}
