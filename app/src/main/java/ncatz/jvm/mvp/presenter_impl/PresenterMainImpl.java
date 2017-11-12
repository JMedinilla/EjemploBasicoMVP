package ncatz.jvm.mvp.presenter_impl;

import android.os.AsyncTask;

import ncatz.jvm.mvp.presenter.PresenterMain;

//El presenter implementa la parte que le corresponde de la interfaz
public class PresenterMainImpl implements PresenterMain {

    //El presenter tiene un objeto de la vista
    private PresenterMain.View view;

    //Recibe la vista a través del constructor
    public PresenterMainImpl(PresenterMain.View view) {
        this.view = view;
    }

    @Override
    public void implValidateText(String text) {
        if (text.isEmpty()) {
            view.viewValidateResponse(false);
        } else {
            try {
                int num = Integer.parseInt(text);
                view.viewValidateResponse(true);
            } catch (NumberFormatException ex) {
                view.viewValidateResponse(false);
            }
        }
    }

    @Override
    public void implHacerAlgoAsincrono() {
        //Aquí se podría hacer un insert en SQLite por ejemplo
        new AlgoAsincrno(view).execute();
    }

    private static class AlgoAsincrno extends AsyncTask<Void, Void, Integer> {

        private PresenterMain.View view;

        private AlgoAsincrno(PresenterMain.View view) {
            this.view = view;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            //Se hace el insert, que devuelve un long con el número de fila o -1 si falla
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 0;
        }

        @Override
        protected void onPostExecute(Integer aVoid) {
            //Y aquí se llama a la vista con el resultado
            view.viewRespuestaDeAlgoAsincrono();
        }
    }
}
