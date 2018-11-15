package com.hornedheck.comeon;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RVAdapter
  extends RecyclerView.Adapter< RVAdapter.TaskViewHolder >{
	
	private List< Task > tasks;

	public RVAdapter(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public void setTasks( List< Task > tasks ){
		this.tasks = tasks;
		this.notifyDataSetChanged();
	}
	
	@NonNull
	@Override
	public TaskViewHolder onCreateViewHolder( @NonNull ViewGroup parent , int viewType ){
		View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.task_card_view , parent , false );
		return new TaskViewHolder( v );
	}
	
	@Override
	public void onBindViewHolder( @NonNull TaskViewHolder holder , int position ){
		holder.taskName.setText( tasks.get( position ).getName() );
		holder.taskDate.setText( tasks.get( position ).getDateForUser() );
		holder.taskDone.setChecked( tasks.get( position ).isDone() );
		holder.taskDone.setTag(tasks.get(position).getId());
	}
	
	@Override
	public int getItemCount(){
		return tasks.size();
	}
	
	@Override
	public void onAttachedToRecyclerView( RecyclerView recyclerView ){
		super.onAttachedToRecyclerView( recyclerView );
	}

	public ArrayList< Task > getSortedTasks(ArrayList< Task > allTasks) {
		Collections.sort(allTasks, new Comparator<Task>() {
			@Override
			public int compare(Task t1, Task t2) {
				return t1.getDate().compareTo(t2.getDate());
			}
		});

		return allTasks;
	}
	
	public static class TaskViewHolder
	  extends RecyclerView.ViewHolder{
		
		CardView cv;
		TextView taskName;
		TextView taskDate;
		CheckBox taskDone;
		
		TaskViewHolder( View itemView ){
			super( itemView );
			cv = itemView.findViewById( R.id.cv );
			taskName = itemView.findViewById( R.id.task_name );
			taskDate = itemView.findViewById( R.id.task_date );
			taskDone = itemView.findViewById( R.id.task_done );
		}
	}
}