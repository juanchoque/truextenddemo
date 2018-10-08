package com.truextend.dev.recipes.error;

import com.truextend.dev.recipes.model.Errors;
import com.truextend.dev.recipes.repositories.ErrorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/***
 * Class save errors in the services
 */
@Component
public class ErrorRecipes {

    @Autowired
    private ErrorsRepository errorsRepository;

    /**
     * Menthod save errors with position exally of error and name data base
     * @param error
     * @param clase
     * @param module
     * @param project
     * @param user
     * @return
     */
    public String insert(Exception error,  Class clase, String module, String project, int user) {
        String message = "";
        String truncateMessage = "";

        Errors errBean = new Errors();

        try {
            StackTraceElement stack[] = error.getStackTrace();

            boolean sal = false;
            short i = 0;
            while (i < stack.length && sal != true) {
                if (clase.toString().indexOf(stack[i].getClassName()) != -1){
                    sal = true;
                }
                i++;
            }

            message = " CLASE.....: " + stack[i - 1].getClassName() +
                      " LINEA.....: " + stack[i - 1].getLineNumber() +
                      " METODO....:  " + stack[i - 1].getMethodName() +
                      " EXCEPCION Y CAUSA.: " + error;

            errBean.setIdUser(user);
            errBean.setModule(module);
            errBean.setTypeProject(project);
            errBean.setDateError(new Date());
            if (message.length() > 999) {
                truncateMessage = message.substring(0, 998);
                errBean.setDescription(truncateMessage);
            } else {
                errBean.setDescription(message);
            }

            //save error
            this.errorsRepository.save(errBean);

            message = String.valueOf(errBean.getId());
        } catch (Exception e) {
            e.printStackTrace();
            message = "-100";
        }
        return message;
    }
}
