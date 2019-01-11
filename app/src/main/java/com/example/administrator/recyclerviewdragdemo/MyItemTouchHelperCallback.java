package com.example.administrator.recyclerviewdragdemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;

public class MyItemTouchHelperCallback extends Callback {
	private ItemTouchMoveListener moveListener;

	public MyItemTouchHelperCallback(ItemTouchMoveListener moveListener) {
		this.moveListener = moveListener;
	}

	@Override
	public int getMovementFlags(RecyclerView recyclerView, ViewHolder holder) {
		//����up,down,left,right
		//������
		int up = ItemTouchHelper.UP;//1  0x0001
		int down = ItemTouchHelper.DOWN;//2 0x0010
//		ItemTouchHelper.LEFT
//		ItemTouchHelper.RIGHT
		//��Ҫ��������ק����������������
		int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
		//��Ҫ������swipe�໬�������ĸ�����
//		int swipeFlags = 0;
		int swipeFlags = ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
		
		
		int flags = makeMovementFlags(dragFlags, swipeFlags);
		return flags;
	}
	
	@Override
	public boolean isLongPressDragEnabled() {
		// �Ƿ���������קЧ��
		return true;
	}

	//���ƶ���ʱ��ص��ķ���--��ק
	@Override
	public boolean onMove(RecyclerView recyclerView, ViewHolder srcHolder, ViewHolder targetHolder) {
		if(srcHolder.getItemViewType()!=targetHolder.getItemViewType()){
			return false;
		}
		// ����ק�Ĺ��̵��в��ϵص���adapter.notifyItemMoved(from,to);
		boolean result = moveListener.onItemMove(srcHolder.getAdapterPosition(), targetHolder.getAdapterPosition());
		return result;
	}

	//�໬��ʱ��ص���
	@Override
	public void onSwiped(ViewHolder holder, int arg1) {
		// �����໬��1.ɾ�����ݣ�2.����adapter.notifyItemRemove(position)
		moveListener.onItemRemove(holder.getAdapterPosition());
	}
	
	
	@Override
	public void onSelectedChanged(ViewHolder viewHolder, int actionState) {
		//�ж�ѡ��״̬
		if(actionState!=ItemTouchHelper.ACTION_STATE_IDLE){
			viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.colorPrimary_pink));
		}
		super.onSelectedChanged(viewHolder, actionState);
	}
	
	@Override
	public void clearView(RecyclerView recyclerView, ViewHolder viewHolder) {
		// �ָ�
		viewHolder.itemView.setBackgroundColor(Color.WHITE);
		viewHolder.itemView.setAlpha(1);//1~0
		viewHolder.itemView.setScaleX(1);//1~0
		viewHolder.itemView.setScaleY(1);
		super.clearView(recyclerView, viewHolder);
	}
	
	@Override
	public void onChildDraw(Canvas c, RecyclerView recyclerView,
			ViewHolder viewHolder, float dX, float dY, int actionState,
			boolean isCurrentlyActive) {
		//dX:ˮƽ�����ƶ������������������������ң���Χ��0~View.getWidth  0~1
		if(actionState==ItemTouchHelper.ACTION_STATE_SWIPE){
			//͸���ȶ���
			float alpha = 1-Math.abs(dX)/viewHolder.itemView.getWidth();
			viewHolder.itemView.setAlpha(alpha);//1~0
			viewHolder.itemView.setScaleX(alpha);//1~0
			viewHolder.itemView.setScaleY(alpha);//1~0
		}
		
		super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState,
				isCurrentlyActive);
	}
	

}
