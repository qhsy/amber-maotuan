package com.ichsy.hrys.common.view.dialog;


import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ichsy.hrys.R;


/**
 * 常用的dialog
 * 
 * @author 赵然
 * 
 */
public class CommonDialog extends AlertDialog {
	/**
	 * 弹框的 标题  内容
	 */
	private TextView tittle,content;
	private TextView leftButton,rightButton,centerButton;
	private LinearLayout twoButtonLay;

	private RelativeLayout rootView;
	private CommonDialogViewType viewType = CommonDialogViewType.TWO;

	private Button closeBtn;

	public CommonDialog(Context context, CommonDialogViewType viewType) {
		super(context, R.style.loginDialog);

		init(context,viewType);
	}

	public CommonDialog(Context context, CommonDialogViewType viewType, boolean cancelable, OnCancelListener cancelListener) {
//		super(context, cancelable, cancelListener);
		super(context,R.style.loginDialog);
		init(context,viewType);
	}

/**
 * 初始化
 */
	private void init(Context context, CommonDialogViewType viewType) {
		this.viewType = viewType;
		show();
		setContentView(R.layout.view_commondialogview);
		rootView = (RelativeLayout) findViewById(R.id.rl_view_commondialog_rootview);
		tittle = (TextView) findViewById(R.id.tv_commondialogview_tittle);
		content = (TextView) findViewById(R.id.tv_commondialogview_content);
		leftButton = (TextView) findViewById(R.id.tv_commondialogview_leftbtn);
		rightButton = (TextView) findViewById(R.id.tv_commondialogview_rightbtn);
		centerButton = (TextView) findViewById(R.id.tv_ommondialogview_center);
		twoButtonLay = (LinearLayout) findViewById(R.id.ll_commondialogview_twobtnlay);
		closeBtn = (Button) findViewById(R.id.btn_commondialogview_close);
		closeBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});


		leftButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		centerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		rightButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		setViewType(viewType);
		
		

	}
	/**
	 * 设置标题和内容的文字
	 * @param tittleText
	 * @param contentText
	 * @return
	 */
  public CommonDialog setTittleAndContent(String tittleText, String contentText){
	  tittle.setText(tittleText);
	  content.setText(contentText);
	  return this;
  }
  /**
   * 设置类型
   * @param viewType
   * @return
   */
  public  CommonDialog setViewType(CommonDialogViewType viewType)
  {
	  this.viewType = viewType;
	  if (viewType == CommonDialogViewType.ONE) {
		  //单按钮
		  twoButtonLay.setVisibility(View.GONE);
		  centerButton.setVisibility(View.VISIBLE);
	}else{
		//双按钮
		twoButtonLay.setVisibility(View.VISIBLE);
		  centerButton.setVisibility(View.GONE);
	}
	  return this;
	  
  }

	public Button getCloseBtn() {
		return closeBtn;
	}


	/**
   * 获取双按钮模式下得 左边的按钮
   * @return
   */
  public TextView getLeftButton(){
	  return leftButton;
  }
  /**
   * 获取双按钮模式下得 右边的按钮
   * @return
   */
  public TextView getRightButton(){
	  return rightButton;
  }
  /**
   * 获取单按钮模式下的按钮
   * @return
   */
  public TextView getCenterButton(){
	  return centerButton;
  }

  public enum CommonDialogViewType{
	  //单按钮
	  ONE
	  //双按钮
	  ,TWO
  }
public void setBackground(int id){
	rootView.setBackgroundResource(id);
}

}
