package com.example.administrator.recyclerviewdragdemo;

public interface ItemTouchMoveListener {
	
	/**
	 * ����ק��ʱ��ص�</br>
	 * �����ڴ˷�������ʵ�֣���ק��Ŀ��ʵ��ˢ��Ч��
	 * @param fromPosition ��ʲôλ����
	 * @param toPosition	��ʲôλ��
	 * @return �Ƿ�ִ����move
	 */
	boolean onItemMove(int fromPosition, int toPosition);

	/**
	 * ����Ŀ���Ƴ��ǻص�
	 * @param position �Ƴ���λ��
	 * @return
	 */
	boolean onItemRemove(int position);
}
