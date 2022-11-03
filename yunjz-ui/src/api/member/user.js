import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listUser(query) {
  return request({
    url: '/member/user/list',
    method: 'get',
    params: query
  })
}

// 条件查询用户列表
export function getListByCondition(query) {
  return request({
    url: '/member/user/list/condition',
    method: 'get',
    params: query
  })
}

//查询可用用户名称列表
export function getUserList() {
  return request({
    url: '/member/user/list/name',
    method: 'get'
  })
}

// 查询【请填写功能名称】详细
export function getUser(userId) {
  return request({
    url: '/member/user/' + userId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addUser(data) {
  return request({
    url: '/member/user',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateUser(data) {
  return request({
    url: '/member/user',
    method: 'put',
    data: data
  })
}

//修改用户状态
export function updateUserStatus(data) {
  return request({
    url: '/member/user/status',
    method: 'post',
    data: data
  })
}

// 删除【请填写功能名称】
export function delUser(userId) {
  return request({
    url: '/member/user/' + userId,
    method: 'delete'
  })
}
