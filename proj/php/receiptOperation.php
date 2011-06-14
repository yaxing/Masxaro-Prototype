<?php
/*
 *  receiptOperation.php -- receipt logic 
 *
 *  Copyright 2011 World Three Technologies, Inc. 
 *  All Rights Reserved.
 * 
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *  Written by Yaxing Chen <Yaxing@masxaro.com>
 * 
 *  
 */

include_once '../config.php';

$method = $_SERVER['REQUEST_METHOD'];

$opcode = $_POST['opcode'];

//$opcode = 'user_get_all_receipt';

$ctrl = new ReceiptCtrl();

switch($opcode){
	case 'new_receipt':
		//1-d array
		$basicInfo = $_POST['receipt'];
		echo $ctrl->insertReceipt($basicInfo, null);
		break;
	
	case 'new_item':
		//2-d array
		$items = $_POST['items'];
		echo $ctrl->insertReceipt(null, $items);
		break;
		
	case 'f_delete_receipt':
		//fake delete one receipt
		echo $ctrl->fakeDelete($_POST['receipt_id']);
		break;
		
	case 'delete_receipt':
		//delete one receipt
		echo $ctrl->realDelete($_POST['receipt_id']);
		break;
		
	case 'recover':
		//recover fake deleted receipt
		echo $ctrl->recoverDeleted($_POST['receipt_id']);
		break;
		
	case 'user_get_all_receipt_basic':
		//user get  all receipts' basic info
		echo json_encode($ctrl->userGetAllReceiptBasic($_POST['acc']));
		break;
		
	case 'user_get_receipt_item':
		//user get items info of one certain receipt
		echo json_encode($ctrl->userGetReceiptItems($_POST['receipt_id']));
		break;
		
	case 'user_get_all_receipt':
		//user get all receipt, with basic info and all items info
		echo json_encode($ctrl->userGetAllReceipt($_POST['acc']));
		break;
		
	case 'user_get_receipt_detail':
		//user get basic info of one certain receipt
		echo json_encode($ctrl->getReceiptDetail($_POST['receipt_id']));
		break;
}

?>