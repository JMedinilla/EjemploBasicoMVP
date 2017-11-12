package ncatz.jvm.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ncatz.jvm.mvp.presenter.PresenterMain;
import ncatz.jvm.mvp.presenter_impl.PresenterMainImpl;

//La vista implementa la parte que le corresponde de la interfaz
public class MainActivity extends AppCompatActivity implements PresenterMain.View {

    @BindView(R.id.editData)
    EditText editData;

    @OnClick({R.id.btnNumber, R.id.btnAsync})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnNumber:
                String text = editData.getText().toString();
                presenterMain.implValidateText(text);
                break;
            case R.id.btnAsync:
                presenterMain.implHacerAlgoAsincrono();
                break;
        }
    }

    //La vista tiene un objeto del presenter
    private PresenterMainImpl presenterMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Se instancia el presenter dándole
        presenterMain = new PresenterMainImpl(this);
    }

    @Override
    public void viewValidateResponse(boolean isNumber) {
        if (isNumber) {
            Toast.makeText(this, "Pos sí", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Pos no", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void viewRespuestaDeAlgoAsincrono() {
        //Por aquí llega la respuesta desde la tarea asíncrona
        //Y es donde se actualizaría la lista, se enviaría un mensaje al usuario, etc
        Toast.makeText(this, "Elemento insertado", Toast.LENGTH_SHORT).show();
    }
}
