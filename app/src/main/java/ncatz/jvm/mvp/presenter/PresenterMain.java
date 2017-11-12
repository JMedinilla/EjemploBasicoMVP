package ncatz.jvm.mvp.presenter;

//Interfaz para el presenter
public interface PresenterMain {

    void implValidateText(String text);

    void implHacerAlgoAsincrono();

    //Interfaz para la vista
    interface View {

        void viewValidateResponse(boolean isNumber);

        void viewRespuestaDeAlgoAsincrono();
    }
}
