package main.com.bsuir.library.command;

import main.com.bsuir.library.command.exception.CommandException;
import main.com.bsuir.library.controller.SessionController;
import main.com.bsuir.library.dao.AbstractDaoController;
import main.com.bsuir.library.dao.factory.DAOFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Саша on 10.05.2017.
 */
public class UpdateUserAvatarCommand extends Command {
    private static final String UPLOAD_DIRECTORY = "upload";

    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    public UpdateUserAvatarCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
    List<Entity> invoke(Map<String, String[]> parameterMap, String dbDataLanguage, SessionController sessionController) throws CommandException {
        List<Entity> entities=null;
        DAOFactory daoFactory = new DAOFactory();
        String[] tablesName = parameterMap.get("table");
        AbstractDaoController controller = daoFactory.getController(tablesName[0]);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        controller.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        String[] userFile=parameterMap.get("userfile");
        int id=sessionController.getUserId();
        if ((userFile != null)&&(id!=0)) {
            try {
                controller.updateFileById(parameterMap,dbDataLanguage,id);
            } catch (Exception e) {
                new CommandException(e);
            }
        }


        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = sessionController.getContext() + File.separator + UPLOAD_DIRECTORY;


        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = sessionController.getFileItem();
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        try {
                            item.write(storeFile);
                        } catch (Exception e) {
                            new CommandException(e);
                        }
                    }
                }
            }
        commandManager.update(this);
        return null;
    }
}
