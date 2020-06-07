package com.example.xiezhe;


/**
 * 维修配件分类
 * @author ASUS
 */

public enum RepairPartTypeEnum {

	/**
	 * 配件分类：发动机类，变速箱类，转向类，刹车类，底盘类，三电类，玻璃类，内饰装潢类，
	 * 外观涂装类，钣金件类，油品类，灯具类，轮胎类，电子电器类，电瓶类，其他类，
	 */
	REPAIR_PART_ENGINE("1", "发动机类"),
	REPAIR_PART_GEARBOX("2", "变速箱类"),
	REPAIR_PART_SWERVE("3", "转向类"),
	REPAIR_PART_BREAK("4", "刹车类"),
	REPAIR_PART_CHASSIS("5", "底盘类"),
	REPAIR_PART_3ELECTRICAL("6", "三电类"),
	REPAIR_PART_GLASS("7", "玻璃类"),
	REPAIR_PART_INDEC("8", "内饰装潢类"),
	REPAIR_PART_EXTERIOR_COAT("9", "外观涂装类"),
	REPAIR_PART_SHEETMETAL("10", "钣金件类"),
	REPAIR_PART_OIL("11", "油品类"),
	REPAIR_PART_LAMP_LANTERN("12", "灯具类"),
	REPAIR_PART_TIRE("13", "轮胎类"),
	REPAIR_PART_ECTRONIC("14", "电子电器类"),
	REPAIR_PART_BATTERY("15", "电瓶类"),
	REPAIR_PART_OTHER("16", "其他类");

	private String code;
	private String title;


	RepairPartTypeEnum(String code, String title) {
		this.code = code;
		this.title = title;
	}
	

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public static String getTitle(String code) {
		for (RepairPartTypeEnum obj : RepairPartTypeEnum.values()) {
			if (obj.getCode() == code) {
				return obj.title;
			}
		}
		return null;
	}
	
	
}
