package com.blueeagle.anydo.ui.main;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.blueeagle.anydo.R;
import com.blueeagle.anydo.Store;
import com.blueeagle.anydo.models.Task;
import com.blueeagle.anydo.utils.ActionFactory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nvtuan on 26/08/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<Task> mTasks;

    TaskAdapter(ArrayList<Task> tasks) {
        if (tasks == null) {
            mTasks = new ArrayList<>();
            return;
        }
        mTasks = tasks;
    }

    void setTasks(ArrayList<Task> tasks) {
        if (tasks == null) return;
        mTasks.clear();
        mTasks.addAll(tasks);
        notifyDataSetChanged();
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = mTasks.get(position);
        holder.mTxtTaskContent.setText(task.content());
        holder.mCbTaskState.setChecked(task.isCompleted());

        if (task.isCompleted()) {
            holder.mTxtTaskContent.setPaintFlags(holder.mTxtTaskContent.getPaintFlags()
                    | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.mTxtTaskContent.setTextColor(Color.parseColor("#AFB4B6"));
        } else {
            holder.mTxtTaskContent.setPaintFlags(holder.mTxtTaskContent.getPaintFlags()
                    & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.mTxtTaskContent.setTextColor(Color.parseColor("#424242"));
        }
    }

    @Override
    public int getItemCount() {
        return mTasks != null ? mTasks.size() : 0;
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.check_box_state)
        CheckBox mCbTaskState;

        @BindView(R.id.text_task_content)
        TextView mTxtTaskContent;

        TaskViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.check_box_state)
        void toggleTask() {
            int id = mTasks.get(getAdapterPosition()).id();
            Store.getInstance().dispatch(ActionFactory.createToggleAction(id));
        }

        @OnClick(R.id.image_delete)
        void deleteTask() {
            int id = mTasks.get(getAdapterPosition()).id();
            Store.getInstance().dispatch(ActionFactory.createDeleteAction(id));
        }
    }
}
