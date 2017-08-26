package com.blueeagle.anydo.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.blueeagle.anydo.R;
import com.blueeagle.anydo.Store;
import com.blueeagle.anydo.Subscriber;
import com.blueeagle.anydo.utils.ActionFactory;
import com.blueeagle.anydo.utils.DialogFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements Subscriber {

    @BindView(R.id.rcv_tasks)
    RecyclerView mRcvTasks;

    @BindView(R.id.edit_text_task_content)
    EditText mEtTaskContent;

    private Store mStore;
    private TaskAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mStore = Store.getInstance();
        initializeViews();
    }

    private void initializeViews() {
        mAdapter = new TaskAdapter(mStore.getState().tasks());
        mRcvTasks.setLayoutManager(new LinearLayoutManager(this));
        mRcvTasks.setNestedScrollingEnabled(false);
        mRcvTasks.setAdapter(mAdapter);

        // Subscribe state change event
        mStore.subscribe(this);

        // Show warning dialog
        DialogFactory.createSimpleOkDialog(this, R.string.dialog_message_info).show();
    }

    @OnClick(R.id.button_add_new_task)
    public void addNewTask() {
        String content = mEtTaskContent.getText().toString();
        if (content.equals("")) {
            Toast.makeText(this, getString(R.string.error_no_content), Toast.LENGTH_LONG).show();
            return;
        }

        // Dispatch add new task action
        mStore.dispatch(ActionFactory.createAddAction(content));
        mEtTaskContent.setText("");
    }

    @Override
    public void onStateChange() {
        // Update UI
        mAdapter.setTasks(mStore.getState().tasks());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStore.unsubscribe(this);
    }
}
