import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listBill(query) {
  return request({
    url: '/account/bill/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getBill(billId) {
  return request({
    url: '/account/bill/' + billId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addBill(data) {
  return request({
    url: '/account/bill',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateBill(data) {
  return request({
    url: '/account/bill',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delBill(billId) {
  return request({
    url: '/account/bill/' + billId,
    method: 'delete'
  })
}
